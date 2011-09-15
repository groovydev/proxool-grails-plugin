package org.groovydev.domain

import grails.test.*

class FooTests extends GrailsUnitTestCase {
    
    def dataSourceUnproxied
    def dataSource
    
    void testFoo() {

        Foo foo = new Foo()
        foo.text = 'hello...'
        
        def result = foo.save()
        
        assertNotNull(result)
        
    }
    
}
