package de.telekom.de.bigBankingBrojekt.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
		
		void addOne(T t) throws SQLException;
		void remove(T t);
		void update(T t);
		List<T> getAll();
		
}
