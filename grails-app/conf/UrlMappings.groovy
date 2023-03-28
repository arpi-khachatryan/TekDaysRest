class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }
//
//        "/api/$id?/$format?" {
//            controller = 'tekEvent'
//            action = [
//                    POST  : 'create',
//                    GET   : 'read',
//                    PUT   : 'update',
//                    DELETE: 'delete',
//            ]
//        }
//
//        "/api/$id?/$format?" {
//            controller = 'tekUser'
//            action = [
//                    POST  : 'create',
//                    GET   : 'read',
//                    PUT   : 'update',
//                    DELETE: 'delete',
//            ]
//        }
//
//        "/api/all/$format?" {
//            controller = 'tekEvent'
//            action = "readAll"
//        }
//
//        "/api/all/$format?" {
//            controller = 'tekUser'
//            action = "readAll"
//        }
//
//
////            "/tekEvent/$id?"(controller:"tekEvent") {
////                action = [GET:'show', PUT:'save', POST:'update', DELETE:'delete']
////            }
//
//
//
        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
//
//
////        "/music/$artist/$album?/$song?"(controller:"store") {
////            action = [GET:'show', PUT:'save', POST:'update', DELETE:'delete']
////        }
//
////        "/music/$artistName/$albumTitle?/$songTitle?"{
////            controller = {
////                if(params.albumTitle && params.songTitle) return 'song'
////                else if(params.albumTitle) return 'album'
////                else return 'artist'
////            }
////            action = [GET:'show', PUT:'save', POST:'update', DELETE:'delete']
////        }
    }
}

