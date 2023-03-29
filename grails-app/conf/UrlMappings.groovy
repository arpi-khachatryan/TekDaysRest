class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')

        "/api/$id?/$format?" {
            controller = 'tekEvent'
            action = [
                    POST  : 'createPost',
                    GET   : 'create',
                    GET   : 'getById',
                    PUT   : 'update',
                    DELETE: 'delete',
            ]
        }

        "/api/$format?" {
            controller = 'tekEvent'
            action = "getAll"
        }

        "/api/$id?/$format?" {
            controller = 'tekUser'
            action = [
                    POST  : 'createPost',
                    GET   : 'create',
                    GET   : 'getById',
                    PUT   : 'update',
                    DELETE: 'delete',
            ]
        }

        "/api/$format?" {
            controller = 'tekUser'
            action = "getAll"
        }

//            "/tekEvent/$id?"(controller:"tekEvent") {
//                action = [GET:'show', PUT:'save', POST:'update', DELETE:'delete']
//            }


////        "/music/$artist/$album?/$song?"(controller:"store") {
////            action = [GET:'show', PUT:'save', POST:'update', DELETE:'delete']
////        }

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

