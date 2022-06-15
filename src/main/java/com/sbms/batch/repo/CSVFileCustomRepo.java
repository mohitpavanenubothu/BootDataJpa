package com.sbms.batch.repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CSVFileCustomRepo {
	
	@Autowired
	private EntityManager entityManager;

	public boolean createTabel(String tableName, String columnsWithType, String columnsWithoutType, String valuesStr,
			BufferedReader fileReader) {

		EntityTransaction txn = null;

		try {
			EntityManager entityManager1 = entityManager.getEntityManagerFactory().createEntityManager();
			txn = entityManager1.getTransaction();
			txn.begin();
			String sql = "create table " + tableName + " ( " + columnsWithType + ") ";
			entityManager1.createNativeQuery(sql).executeUpdate();

			String insertQry = "Insert into " + tableName + " ( " + columnsWithoutType + ") values  " + valuesStr + "";

			Session hibernateSession = entityManager1.unwrap(Session.class);
			hibernateSession.doWork(connection -> {

				PreparedStatement preparedStatement = connection.prepareStatement(insertQry);
				String lineText = null;
				int count = 0;
				int batchSize = 20;

				try {
					fileReader.readLine(); // skip header line

					while ((lineText = fileReader.readLine()) != null) {
						String[] data = lineText.split(",");
						for (int x = 0; x < data.length; x++) {
							preparedStatement.setString((x + 1), data[x]);
						}
						preparedStatement.execute();
						if (count % batchSize == 0) {
							preparedStatement.executeBatch();
						}
						count++;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			});

			txn.commit();
			return true;
		} catch (Throwable e) {
			if (txn != null && txn.isActive()) {
				txn.rollback();
			}
			return false;
		}

	}
}
