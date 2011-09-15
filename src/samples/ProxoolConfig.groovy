proxool {

    // The minimum number of connections we will keep open, regardless of whether anyone needs them or not. Default is 5.
    minimumConnectionCount = 10
    
    // If there are fewer than this number of connections available then 
    // we will build some more (assuming the maximum-connection-count is not exceeded). 
    // For example. Of we have 3 active connections and 2 available, but our prototype-count is 4 then it will attempt to build another 2. 
    // This differs from minimum-connection-count because it takes into account the number of active connections. 
    // minimum-connection-count is absolute and doesn't care how many are in use. 
    // prototype-count is the number of spare connections it strives to keep over and above the ones that are currently active. 
    // Default is 0.
    prototypeCount = 0

    // This is the maximum number of connections we can be building at any one time. 
    // That is, the number of new connections that have been requested but aren't yet available for use. 
    // Because connections can be built using more than one thread (for instance, when they are built on demand) 
    // and it takes a finite time between deciding to build the connection and it becoming available 
    // we need some way of ensuring that a lot of threads don't all decide to build a connection at once. 
    // Default is 10.
    simultaneousBuildThrottle = 10

    // The maximum number of connections to the database. Default is 15.
    maximumConnectionCount = 40

    // If the housekeeper comes across a thread that has been active for longer than this then it will kill it. 
    // So make sure you set this to a number bigger than your slowest expected response! Default is 5 minutes.
    maximumActiveTime = 10 * 60  

    // If the house keeping thread finds and idle connections it will test them with this SQL statement. 
    // It should be very quick to execute. Something like checking the current date or something. If not defined then this test is omitted.
    //houseKeepingTestSql = 'select 1' 

    // The sample length when taking statistical information, comma-delimited. 
    // For example: '10s,15m' would mean take samples every 10 seconds and every 15 minutes. 
    // Valid units are s(econds), m(inutes), h(ours) and d(ays). Default is null (no statistics).    
    statistics = '1m'
    
    // Whether statistics are logged as they are produced. Range: DEBUG, INFO, WARN, ERROR, FATAL.
    statisticsLogLevel = 'INFO'
    
    // If you set this to true then each connection is tested
    //testAfterUse = false
    
    // If you set this to true then each connection is tested
    //testBeforeUse = false
    
    // If true then each SQL call gets logged (DEBUG level) along with the execution time.
    trace = false
    
    // Either false (quiet) or true (loud). Default is false.
    verbose = false

    driverProperties {

        driverUrl = "jdbc:hsqldb:mem:testDb"
        driver = 'org.hsqldb.jdbcDriver'
    
        user = "sa"
        password = ""
    }
}
    