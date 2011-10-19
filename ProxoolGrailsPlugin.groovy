import grails.util.GrailsUtil

import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.logicalcobwebs.proxool.ProxoolFacade

class ProxoolGrailsPlugin {
    
    Logger log = Logger.getLogger('grails.plugins.proxool.ProxoolGrailsPlugin')
    
    // the plugin version
    def version = "0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def loadBefore = ['dataSource']
    
    def author = "Karol Balejko"
    def authorEmail = "kb@groovydev.org"
    def title = "Proxool jdbc connection pool plugin"
    def description = 'Grails plugin for proxool jdbc connection pool.'

    def documentation = "https://github.com/groovydev/proxool-grails-plugin/blob/master/README.md"
    def license = "APACHE"
    def issueManagement = [ system: "github", url: "https://github.com/groovydev/proxool-grails-plugin/issues" ]
    def scm = [ url: "https://github.com/groovydev/proxool-grails-plugin" ]

    def doWithSpring = {
        
        // load user config
        def slurper = new ConfigSlurper(GrailsUtil.environment)
        def config
        try {
            config = slurper.parse(application.classLoader.loadClass('ProxoolConfig'))
        } catch (e) {
            config = [:]
        }

        def driver
        def driverUrl
        String alias
        
        Properties info = new Properties()
        config.proxool.each{key,value->
            if (key == 'driverProperties') {
                value.each {k,v->
                    if (k == 'driver') {
                        driver = v
                    } else if (k == 'driverUrl') {
                        driverUrl = v
                    } else {
                        info.setProperty(k, v.toString())
                    }
                }
            } else if (key == 'alias') {
                alias = value
            } else {
                String[] arr = StringUtils.splitByCharacterTypeCamelCase(key)
                def name = arr.collect {it.toLowerCase()}.join('-')
                def propertyName = "proxool.${name}".toString() 
                info.setProperty(propertyName, value.toString())
            }
        }
        
        alias = alias ?: "grails-pool" 
        String url = "proxool.${alias}:${driver}:${driverUrl}"
        
        def exists = ProxoolFacade.getAliases().toList().find {it == alias}
        
        if (exists) {
            ProxoolFacade.redefineConnectionPool(url, info)
            log.info "Proxool connection pool redefined. alias is ${alias}"
        } else {
            ProxoolFacade.registerConnectionPool(url, info)
            log.info "Proxool connection pool registered. alias is ${alias}"
        }
    }

}
