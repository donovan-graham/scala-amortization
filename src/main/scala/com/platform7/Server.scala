package com.platform7

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.{Await, Future}

object Server extends App {

  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      Future {
        val response = http.Response(req.version, http.Status.Ok)
        response.setContentString("Boom")
        response.setContentTypeJson()
        response
      }
  }

  val server = Http.serve(":8080", service)
  Await.ready(server)
}