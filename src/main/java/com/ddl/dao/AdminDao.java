package com.ddl.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ddl.model.Admin;

@Repository(value="adminDao")
public class AdminDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<Admin> getAllAdmin() {
		try {
			Session session = getSession();
			session.beginTransaction();
			List<Admin> list = session.createSQLQuery("select * from Admin").addEntity(Admin.class).list();
			session.getTransaction().commit();
			System.out.println(list);
			return list;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public boolean modifyAdmin(Admin admin) {
		try {
			Session session = getSession();
			session.beginTransaction();
			session.update(admin);
			session.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean addAdmin(Admin admin) {
		return save(admin);
	}

	public Boolean deleteAdmin(Admin admin) {
		try {
			Session session = getSession();
			session.beginTransaction();
			session.delete(admin);
			session.getTransaction().commit();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean checkAdmin(Admin admin) {
		try {
			Session session = getSession();
			session.beginTransaction();

			Query query = session.createSQLQuery("SELECT count(*) FROM Admin where admin = '" + admin.getAdmin()
					+ "' and password = '" + admin.getPassWord() + "'");
			session.getTransaction().commit();
			// BigInteger
			int num = Integer.valueOf(query.uniqueResult().toString());
			
			if (num > 0) {
				return true;
			}

			return false;
		} catch (Exception e) {
			System.err.println("checkAdmin:"+e.getMessage());
			return false;
		}
	}
	
	private boolean save(Object object) {
		try {
			Session session = getSession();
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}