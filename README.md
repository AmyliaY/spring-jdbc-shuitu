# spring-jdbc-shuitu
结合主流JDBC框架的优点，自己构思的一个简单spring-jdbc框架。

# 思路
1、制定QueryRule规范，定义了很多查询规则常量，定义了查询规则保存的方法
2、提供一个抽象的BaseDaoSupport给开发者去实现，目前只支持单表操作，传两个泛型值，一个是主键，一个是实体类
3、把实体类的配置信息解析成一个EntityOperation对象，同时实现了ORM的自动化
4、在BaseDaoSupport中调用查询方法，把QueryRule作为参数传入
5、将传入的QueryRule交给QueryRuleSqlBulider，构建成相应的sql语句（根据where、order关键字做了一定的拆分）
6、将零散的sql语句拼接成一个完整的sql
7、将sql语句交给JdbcTemplate执行
8、调用EntityOperation的ORM过程
9、返回结果，由于都是单表操作，且规定了泛型，所以不需要任何强制类型转换