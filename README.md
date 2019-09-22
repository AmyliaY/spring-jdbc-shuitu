# spring-jdbc-shuitu
基于spring-jdbc结合主流JDBC框架的优点，实现的一个简单高效易用的JDBC框架。

# 特点
1. 性能好，不经过二次处理
2. 单表操作实现NoSql（用户只需链式编程调用相应的方法，程序会自动完成sql语句的拼接）
3. 自动完成ORM过程（使用反射机制将数据库字段和POJO的属性相对应，将结果集中的元数据自动封装成实例化POJO对象集合并返回）
4. 支持读写分离，可在application-db.xml中为读写分别设置数据源，默认是读写同源

# 原则
1. 约定优于配置
2. 一个DAO只操作一张表
3. 修改删除根据主键操作
4. 实体类必须实现序列化，以便在网络上进行传输
5. 默认只支持Java八大基本数据类型 加String，日期建议使用时间戳

# 思路
1. 制定QueryRule规范，定义了很多查询规则常量，定义了查询规则保存的方法
2. 提供一个抽象的BaseDaoSupport给开发者去实现，目前只支持单表操作，传两个泛型值，一个是主键，一个是实体类
3. 把实体类的配置信息解析成一个EntityOperation对象，同时实现了ORM的自动化
4. 在BaseDaoSupport中调用查询方法，把QueryRule作为参数传入
5. 将传入的QueryRule交给QueryRuleSqlBulider，构建成相应的sql语句（根据where、order关键字做了一定的拆分）
6. 将零散的sql语句拼接成一个完整的sql
7. 将sql语句交给JdbcTemplate执行
8. 调用EntityOperation的ORM过程
9. 返回结果，由于都是单表操作，且规定了泛型，所以不需要任何强制类型转换

# 待完善
1. 分库分表