package com.tekDays

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import tekdays.TekEvent

@Transactional(readOnly = true)
class TekEventController {

    static responseFormats = ['json', 'xml', 'html', 'text']
    static allowedMethods = [create: "GET", createPost: "POST", getById: "GET", getAll: "GET", update: "PUT", delete: "DELETE"]

    //POST//http://localhost:8080/TekDays_Rest/tekEvent/create?name=nare&format=xml
    @Transactional
    def create() {
        TekEvent tekEvent = new TekEvent(name: params.name)
        tekEvent.save(flush: true)
        render("NAME= ${tekEvent.name}")
    }

    //POST//curl -X POST http://localhost:8080/TekDays_Rest/tekEvent/c -H 'Content-Type: application/json' -d ' {name:event,personal_number:97312525}'
    @Transactional
    def creatPost() {
        def slurped = new JsonSlurper()
        def requestMap = slurped.parseText(request?.JSON?.toString())
        def object = requestMap["name"]
        TekEvent tekEvent = new TekEvent(name: object)
        tekEvent.save flush: true
        render("NAME= ${tekEvent.name}") as JSON
    }

    //GET//http://localhost:8080/TekDays_Rest/tekEvent/getById?id=2&format=xml
    @Transactional
    def getById(String id) {
        TekEvent tekEvent = TekEvent.findById(id)
        if (tekEvent) {
            withFormat {
                xml { render(tekEvent as XML) }
                json {
                    def jsonify = tekEvent as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                }
                html {
                    render {
                        H3(tekEvent, ["style": "font-family:Monospace"])
                    }
                }
            }
        } else response.sendError 404
    }

    //GET//http://localhost:8080/TekDays_Rest/tekEvent/getAll?format=json
    def getAll() {
        def tekEvents = TekEvent.list()
        //list.each { it.users = "***" }
        if (tekEvents) {
            withFormat {
                //build  xml object //only id and name
//                xml {
//                    render(contentType: "text/xml") {
//                        AllTekEvents() {
//                            for (event in tekEvents) {
//                                tekEvent(id: event.id) {
//                                    name(name: event.name)
//                                }
//                            }
//                        }
//                    }
//                }
                //auto build xml object
                xml {
                    render tekEvents as XML
                }
                json {
                    def jsonify = tekEvents as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                    render(['success': 'ok', data: tekEvents] as JSON)
//                    render(contentType: "text/json") {
//                        for (event in tekEvents) {
//                            render(['success': 'ok', data: [id: event.id, name: event.name]] as JSON)
//                        }
//                    }
                }
                text {
                    StringBuilder stringBuilder = new StringBuilder("")
                    tekEvents.each {
                        stringBuilder.append("NAME-> " + it.name)
                        stringBuilder.append("<br>")
                    }
                    render(stringBuilder.toString())
                }
                html {
                    render {
                        tekEvents.each { H3(it, ["style": "font-family:Monospace"]) }
                    }
                }
            }
        } else response.sendError 404
    }

    //PUT//http://localhost:8080/TekDays_Rest/tekEvent/update/2?name=party&format=json
    @Transactional
    def update(String id) {
        TekEvent tekEvent = TekEvent.findById(id)
        if (tekEvent) {
            if (params.name) {
                tekEvent.name = params.name
            }
            tekEvent.save(flush: true)
            withFormat {
                xml {
//                    if (tekEvent.hasErrors()){
//                        render tekEvent.errors as XML
//                    }
                    render(tekEvent as XML)
                }
                json {
                    def jsonify = tekEvent as JSON
                    jsonify.prettyPrint = true
                    render jsonify
                }
                html {
                    render {
                        H3(tekEvent, ["style": "font-family:Monospace"])
                    }
                }
            }
        } else response.sendError 404
    }

    //DELETE//http://localhost:8080/TekDays_Rest/tekEvent/delete/1
    @Transactional
    def delete(String id) {
        TekEvent tekEvent = TekEvent.findById(id)
        if (tekEvent) {
            String eventData = tekEvent.toString()
            tekEvent.delete(flush: true)
            render(eventData + " =>DELETED")
        } else response.sendError 404
    }
}
