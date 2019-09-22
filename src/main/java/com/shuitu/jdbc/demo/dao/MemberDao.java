package com.shuitu.jdbc.demo.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.shuitu.demo.entity.Member;
import com.shuitu.jdbc.framework.BaseDaoSupport;
import com.shuitu.jdbc.framework.QueryRule;

@Repository
public class MemberDao extends BaseDaoSupport<Member, Long> {

	@Override
	protected String getPKColumn() {
		return "id";
	}

	/**
	 * 读写同源
	 */
	@Resource(name = "dataSource")
	protected void setDataSource(DataSource dataSource) {
		super.setDataSourceReadOnly(dataSource);
		super.setDataSourceWrite(dataSource);
	}
	
	/**
	 * 支持读写分离
	 */
/*	@Resource(name = "dataSourceWrite")
	protected void setDataSourceWrite(DataSource dataSourceWrite) {
		// TODO Auto-generated method stub
		super.setDataSourceWrite(dataSourceWrite);
	}

	@Resource(name = "dataSourceReadOnly")
	protected void setDataSourceReadOnly(DataSource dataSourceReadOnly) {
		// TODO Auto-generated method stub
		super.setDataSourceReadOnly(dataSourceReadOnly);
	}*/

	public List<Member> selectByName(String name) throws Exception {
		QueryRule queryRule = QueryRule.getInstance().andEqual("name", name).addAscOrder("name").addAscOrder("id");

		return super.find(queryRule);
	}

	public List<Member> selectAll() throws Exception {
		return super.getAll();
	}

	public boolean insterOne(Member m) throws Exception {
		//通过动态修改表名实现分表
		//super.setTableName("coder_19");
		Long id = super.insertAndReturnId(m);
		m.setId(id);
		return id > 0;
	}

	public boolean updataOne(Member m) throws Exception {
		long count = super.update(m);
		return count > 0;
	}

	public boolean deleteOne(Member m) throws Exception {
		long count = super.delete(m);
		return count > 0;
	}

}
