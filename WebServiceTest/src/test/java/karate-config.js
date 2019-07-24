function fn() {
    var env = karate.env; // get java system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev'; // a custom 'intelligent' default
    }
    var config = { // base config JSON

        baseUrl: '',
        contentTypeUrlEncoded:'\'application/x-www-form-urlencoded\'',
    sqlScript : java.lang.System.getProperty('user.dir')+'/src/test/java/sqlscripts/',


    };


// don't waste time waiting for a connection or if servers don't respond within 5 seconds
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);


    return config;
}