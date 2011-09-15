Grails plugin for proxool jdbc connection pool.
===============================================

http://proxool.sourceforge.net/index.html

DataSource.groovy
=================

Example in grails-app/conf/DataSource.groovy

    dataSource {
        pooled = false
        dbCreate = "update"
        url = "proxool.grails-pool"
        driverClassName = 'org.logicalcobwebs.proxool.ProxoolDriver'
    }

ProxoolConfig.groovy
====================

Example in grails-app/conf/ProxoolConfig.groovy

proxool {

    minimumConnectionCount = 5
    maximumConnectionCount = 10
    statistics = '15s'
    statisticsLogLevel = 'INFO'
    
    driverProperties {

        driverUrl = "jdbc:hsqldb:mem:testDb"
        driver = 'org.hsqldb.jdbcDriver'
    
        user = "sa"
        password = ""
    }
}

Proxool config accepts the following properties:

proxool {
    alias
        Proxool pool alias name (default: grails-pool). The jdbc connection url pattern is proxool.<alias>
        
    fatalSqlException
        A comma separated list of message fragments. When an SQLException occurs its message is compared to each of these fragments. If it contains any of them (case sensitive) then it is detected as a Fatal SQL Exception. This causes that connection to be discarded. Regardless of what happens, the exception is thrown again so that the user knows what has happened. You can optionally configure a different exception to be thrown (see fatal-sql-exception-wrapper-class property) Default is null.
    
    fatalSqlExceptionWrapperClass
    
        If you have configured fatal-sql-exception then the default behaviour is to discard the exception that causes the fatal SQLException and then just throw the original exception to the user. Using this property you can wrap the SQLException up inside another exception. This exception can be anything you want as long as it either extends SQLException or RuntimeException. Proxool provides two classes which you can use if you don't want to build your own: FatalSQLException and FatalRuntimeException. To make use of those you should set this property to either 'org.logicalcobwebs.proxool.FatalSQLException' or 'org.logicalcobwebs.proxool.FatalRuntimeException' as appropriate. Default is null (fatal SQLExceptions are not wrapped). Default is null.
    
    houseKeepingSleepTime
    
        How long the house keeping thread sleeps for (milliseconds). The house keeper is responsible for checking the state of all the connections and tests whether any need to be destroyed or created. Default is 30 seconds.
    
    houseKeepingTestSql
    
        If the house keeping thread finds and idle connections it will test them with this SQL statement. It should be very quick to execute. Something like checking the current date or something. If not defined then this test is omitted.
    
    injectableConnectionInterface
    
        Allows Proxool to implement methods defined in the delegate Connection object. See Injectable Interfaces.
    
    injectableStatementInterface
    
        Allows Proxool to implement methods defined in the delegate Statement object. See Injectable Interfaces.
    
    injectablePreparedStatementInterface:
    
        Allows Proxool to implement methods defined in the delegate PreparedStatement object. See Injectable Interfaces.
    
    injectableCallableStatementInterface:
    
        Allows Proxool to implement methods defined in the delegate CallableStatement object. See Injectable Interfaces.
    
    jmx
    
        If true the pool will be registered as an MBean to a JMX server with the following object name: "Proxool:type=Pool, name=<alias>". Default is false.
    
    jmxAgentId
    
        A comma separated list of JMX agent ids (as used by MBeanServerFactory.findMBeanServer(String agentId) ) to register the pool to. This property is only used if the "jmx" property is set to "true". All registered JMX servers will be used if this property is not set.
    
    jndiName
    
        See DataSource.
    
    maximumActiveTime
    
        If the housekeeper comes across a thread that has been active for longer than this then it will kill it. So make sure you set this to a number bigger than your slowest expected response! Default is 5 minutes.
    
    maximumConnectionCount
    
        The maximum number of connections to the database. Default is 15.
    
    maximumConnectionLifetime
    
        The maximum amount of time that a connection exists for before it is killed (milliseconds). Default is 4 hours.
    
    minimumConnectionCount
    
        The minimum number of connections we will keep open, regardless of whether anyone needs them or not. Default is 5.
    
    overloadWithoutRefusalLifetime
    
        This helps us determine the pool status. If we have refused a connection within this threshold (milliseconds) then we are overloaded. Default is 60 seconds.
    
    prototypeCount
    
        If there are fewer than this number of connections available then we will build some more (assuming the maximum-connection-count is not exceeded). For example. Of we have 3 active connections and 2 available, but our prototype-count is 4 then it will attempt to build another 2. This differs from minimum-connection-count because it takes into account the number of active connections. minimum-connection-count is absolute and doesn't care how many are in use. prototype-count is the number of spare connections it strives to keep over and above the ones that are currently active. Default is 0.
    
    recentlyStartedThreshold
    
        This helps us determine whether the pool status is up, down or overloaded. As long as at least one connection was started within this threshold (milliseconds) or there are some spare connections available then we assume the pool is up. Default is 60 seconds.
    
    simultaneousBuildThrottle
    
        This is the maximum number of connections we can be building at any one time. That is, the number of new connections that have been requested but aren't yet available for use. Because connections can be built using more than one thread (for instance, when they are built on demand) and it takes a finite time between deciding to build the connection and it becoming available we need some way of ensuring that a lot of threads don't all decide to build a connection at once. (We could solve this in a smarter way - and indeed we will one day) Default is 10.
    
    statistics
    
        The sample length when taking statistical information, comma-delimited. For example: '10s,15m' would mean take samples every 10 seconds and every 15 minutes. Valid units are s(econds), m(inutes), h(ours) and d(ays). Default is null (no statistics).
    
    statisticsLogLevel
    
        Whether statistics are logged as they are produced. Range: DEBUG, INFO, WARN, ERROR, FATAL. Not to be confused with the level used with the general log. You have to configure that separately. Default is null (no logging).
    
    testBeforeUse
    
        If you set this to true then each connection is tested (with whatever is defined in house-keeping-test-sql) before being served. If a connection fails then it is discarded and another one is picked. If all connections fail a new one is built. If that one fails then you get an SQLException saying so.
    
    testAfterUse:
    
        If you set this to true then each connection is tested (with whatever is defined in house-keeping-test-sql) after it is closed (that is, returned to the connection pool). If a connection fails then it is discarded.
    
    trace
    
        If true then each SQL call gets logged (DEBUG level) along with the execution time. You can also get this information by registering a ConnectionListener (see ProxoolFacade). Default is false.
    
    verbose
    
        Either false (quiet) or true (loud). Default is false.


    driverProperties {

        driverUrl
            jdbc connection url
             
        driver
            driver class name
    
        user
            database username
            
        password
            database password
            
        ...
            other properties are populated to jdbc driver
    }
        
}