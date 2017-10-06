package com.platform7.app

import com.platform7.controllers.MainController
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

class AppServer extends HttpServer {

  override protected def defaultFinatraHttpPort: String = ":8080"

  override protected def defaultHttpServerName: String = "Amortization Server"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add(new MainController)
  }
}
