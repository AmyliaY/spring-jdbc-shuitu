package com.shuitu.jdbc.demo.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.shuitu.demo.entity.Coder;
import com.shuitu.jdbc.framework.BaseDaoSupport;
import com.shuitu.jdbc.framework.QueryRule;

/**
* @author 全恒
*/

@Repository
public class CoderDao extends BaseDaoSupport<Coder, Long> {

	@Override
	protected String getPKColumn() {
		return "id";
	}

	@Resource(name = "dataSource")
	protected void setDataSource(DataSource dataSource) {
		super.setDataSourceReadOnly(dataSource);
		super.setDataSourceWrite(dataSource);
	}

	public List<Coder> selectByName(String name) throws Exception{
		QueryRule queryRule = QueryRule.getInstance().andEqual("name", name);
		return super.find(queryRule);
	}

	public List<Coder> selectAll() throws Exception {
		QueryRule queryRule = QueryRule.getInstance();
		return super.find(queryRule);
	}

	public boolean insterOne(Coder data) throws Exception {
		return super.insertOne(data);
	}

	public boolean updataOne(Coder data) throws Exception {
		long count = super.update(data);
		return count > 0;
	}

	public boolean deleteOne(Coder data) throws Exception {
		long count = super.delete(data);
		return count > 0;
	}
	
}
