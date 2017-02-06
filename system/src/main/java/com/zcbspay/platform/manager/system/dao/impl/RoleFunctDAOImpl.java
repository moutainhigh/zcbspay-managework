package com.zcbspay.platform.manager.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.manager.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.manager.system.bean.RoleFunctBean;
import com.zcbspay.platform.manager.system.dao.RoleFunctDAO;
import com.zcbspay.platform.manager.system.pojo.PojoRoleFunct;
import com.zcbspay.platform.manager.utils.BeanCopyUtil;

@Repository
@SuppressWarnings("all")
public class RoleFunctDAOImpl extends HibernateBaseDAOImpl<PojoRoleFunct> implements RoleFunctDAO{
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void deleteRoleFunction(Long roleId) {
		updateByHQL("delete from PojoRoleFunct rf where rf.roleId = ?",new Object[]{roleId});
	}

	@Override
	public List<?> findByProperty(Long roleId) {
		Object[] paramaters = null;
		String sql;
		if(roleId != null){
			sql = "select rm from PojoRoleFunct rm where rm.roleId=" + roleId;
		}else{
			sql = "select rm from PojoRoleFunct";
		}
		return queryByHQL(sql, paramaters);
	}

	@Override
	public void save(List<PojoRoleFunct> functList) {
		Object[] paramaters = null;
		String sql;
		if(functList.size() > 0){
			for(PojoRoleFunct roleFunct : functList){
				sql = "insert into T_ROLE_FUNCT(ROLE_ID,FUNCT_ID) values ("+ roleFunct.getRoleId()+","+roleFunct.getFunctId()+")";
				queryBySQL(sql, paramaters);
			}
		}
	}


	@Override
	public List<?> findRoleFunctByRoleIds(List<Long> roleIdlist) {
		Object[] paramaters = null;
		String sql;
		List<?> list = new ArrayList<PojoRoleFunct>();
//		Criteria crite = this.getSession().createCriteria(PojoRoleFunct.class);
		for(Long roleId : roleIdlist){
			
//			crite.add(Restrictions.eq("roleId",roleId));
//			Object uniqueResult = crite.uniqueResult();
//			RoleFunctBean roleFunct = BeanCopyUtil.copyBean(RoleFunctBean.class, uniqueResult);
//			list.add(roleFunct);
			sql = "select rm from PojoRoleFunct rm where rm.roleId=" + roleId;
			 list = queryByHQL(sql, paramaters);
		}
		return list;
	}
}
