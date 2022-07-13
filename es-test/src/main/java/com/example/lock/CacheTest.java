package com.example.lock;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.alibaba.fastjson.JSON;

/**
 * @author simple
 * @date 2022/3/19 11:54
 */
public class CacheTest extends GenericDao {
	private MyGenericDao dao = new MyGenericDao();
	private Map<SqlPair, Object> map = new ConcurrentHashMap<>();
	private static ReadWriteLock rw = new ReentrantReadWriteLock();


	public static void main(String[] args) throws InterruptedException {
		CacheTest cacheTest = new CacheTest();
//		List<Book> books = cacheTest.queryList(Book.class, "select * from book where id = ?;", "6");
//		System.out.println(JSON.toJSON(books));
//
//		List<Book> books1 = cacheTest.queryList(Book.class, "select * from book where id = ?;", "6");
//		System.out.println(JSON.toJSON(books1));
//
//		List<Book> books2 = cacheTest.queryList(Book.class, "select * from book where id = ?;", "1");
//		System.out.println(JSON.toJSON(books2));
		new Thread(()->{
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			List<Book> books3 = cacheTest.queryList(Book.class, "select * from book where id = ?;", "1");
			System.out.println(JSON.toJSON(books3));
		}).start();
		int update = cacheTest.update("UPDATE demo.book SET name = '围城', category = '小说6' WHERE id = ?; ", "1");
		List<Book> books3 = cacheTest.queryList(Book.class, "select * from book where id = ?;", "1");
		System.out.println(JSON.toJSON(books3));

	}

	@Override
	public <T> List<T> queryList(Class<T> beanClass, String sql, Object... args) {
		SqlPair sqlPair = new SqlPair(sql, args);
		rw.readLock().lock();
		Object value = map.get(sqlPair);
		try {
			if (value != null) {
				return (List<T>) value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.readLock().unlock();
		}
		rw.writeLock().lock();
		try {
			if (value == null) {
				List<T> ts = super.queryList(beanClass, sql, args);
				map.put(sqlPair, ts);
				return ts;
			}
		} finally {
			rw.writeLock().unlock();
		}
		return (List<T>) value;
	}

	@Override
	public <T> T queryOne(Class<T> beanClass, String sql, Object... args) {
		return super.queryOne(beanClass, sql, args);
	}

	@Override
	public int update(String sql, Object... args) throws InterruptedException {
		Thread.sleep(3000);
		rw.writeLock().lock();
		try {
			map.clear();
			int update = super.update(sql, args);
			Thread.sleep(3000);
			return update;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return 0;
		} finally {
			rw.writeLock().unlock();
		}
	}

	class SqlPair {
		private String sql;
		private Object[] args;

		public SqlPair(String sql, Object[] args) {
			this.sql = sql;
			this.args = args;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			SqlPair sqlPair = (SqlPair) o;
			return Objects.equals(sql, sqlPair.sql) && Arrays.equals(args, sqlPair.args);
		}

		@Override
		public int hashCode() {
			int result = Objects.hash(sql);
			result = 31 * result + Arrays.hashCode(args);
			return result;
		}
	}
}
