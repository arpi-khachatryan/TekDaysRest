package com.tekDays

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import tekdays.TekEvent
import tekdays.TekUser


@Transactional(readOnly = true)
class TekUserController {

    static responseFormats = ['json', 'xml', 'html', 'text']
    static allowedMethods = [ajaxGet: "GET", create: "GET", createPost: "POST", getById: "GET", getAll: "GET", update: "PUT", delete: "DELETE"]

    def ajax() {
    }

    def ajaxGet = {
        def tekUsers = TekUser.list()
        if (tekUsers) {
            withFormat {
                xml {
                    render tekUsers as XML
                }
                json {
//                    def jsonify = tekUsers as JSON
//                    jsonify.prettyPrint = true
//                    render jsonify
                    render(['success': 'ok', data: tekUsers] as JSON)
                }
            }
        } else {
            response.sendError 404
        }
    }

    //POST//http://localhost:8080/TekDays_Rest/tekUser/create?fullName=nare&format=xml
    @Transactional
    def create() {
        TekUser tekUser = new TekUser(fullName: params.fullName, userName: params.username, password: params.password, email: params.email)
        tekUser.save(flush: true)
        render("FULLNAME= ${tekUser.fullName}")
    }

    //POST//curl -X POST http://localhost:8080/TekDays_Rest/tekUser/c -H 'Content-Type: application/json' -d ' {fullName:name,username:user,password:12345,email:name@gmail.com}'
    @Transactional
    def creatPost() {
        def slurped = new JsonSlurper()
        def requestMap = slurped.parseText(request?.JSON?.toString())
        def fullName = requestMap["fullName"]
        def userName = requestMap["userName"]
        def password = requestMap["password"]
        def email = requestMap["email"]
        TekUser tekUser = new TekUser(fullName: fullName, userName: userName, password: password, email: email)
        tekUser.save flush: true
        render("NAME= ${tekUser.fullName}") as JSON
    }

    //curl  -H "Accept: application/json"  http://localhost:8080/TekDays_Rest/tekUser/getById?id=1&format=json
    //GET//http://localhost:8080/TekDays_Rest/tekUser/getById?id=2&format=xml
    @Transactional
    def getById(String id) {
        TekUser tekUser = TekUser.findById(id)
        if (tekUser) {
            withFormat {
                xml { render(tekUser as XML) }
                json {
                    def jsonify = tekUser as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                }
                html {
//                    render {
//                        H3(tekUser, ["style": "font-family:Monospace"])
//                    }
                    render(view: "index")
//                    respond tekUser, view: 'index'
                }
            }
        } else response.sendError 404
    }

    //GET//http://localhost:8080/TekDays_Rest/tekUser/getAll?format=json
    def getAll() {
        def tekUsers = TekUser.list()
        //list.each { it.users = "***" }
        if (tekUsers) {
            withFormat {
                //build  xml object
                xml {
                    render(contentType: "text/xml") {
                        AllTekUsers() {
                            for (user in tekUsers) {
                                User(id: user.id) {
                                    FullName(fullName: user.fullName)
                                }
                            }
                        }
                    }
                }
                //auto build xml object
                xml {
                    render tekUsers as XML
                }
                json {
                    def jsonify = tekUsers as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                    //render (['success':'ok',data:tekUsers] as JSON)
                }
                text {
                    StringBuilder stringBuilder = new StringBuilder("")
                    tekUsers.each {
                        stringBuilder.append("  FULLNAME-> " + it.fullName)
                        stringBuilder.append("<br>")
                    }
                    render(stringBuilder.toString())
                }
                html {
                    render {
                        tekUsers.each { H3(it, ["style": "font-family:Monospace"]) }
                    }
                }
            }
//            client test
//            def url = new URL("http://localhost:8080/api/1_5e5a2dc4-cda1-41cf-be94-583472c77408")
//            def conn = url.openConnection()
//            conn.addRequestProperty("accept","application/json")
//            def artist=conn.content
//            println "Artist Name = ${artist}"
        } else response.sendError 404
    }

//PUT//http://localhost:8080/TekDays_Rest/tekUser/update/2?name=party
    @Transactional
    def update(String id) {
        TekUser tekUser = TekUser.findById(id)
        if (tekUser) {
            if (params.fullName) {
                TekUser.fullName = params.fullName
            }
            tekUser.save(flush: true)

            withFormat {
                xml { render(tekUser as XML) }
                json {
                    def jsonify = tekUser as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                }
                html {
                    render {
                        H3(tekUser, ["style": "font-family:Monospace"])
                    }
                }
            }
        } else response.sendError 404
    }

//DELETE//http://localhost:8080/TekDays_Rest/tekUser/delete/1
    @Transactional
    def delete(String id) {
        TekUser tekUser = TekUser.findById(id)
        if (tekUser) {
            String userData = tekUser.toString()
            tekUser.delete(flush: true)
            render(userData + " =>DELETED")
        } else response.sendError 404
    }
}
