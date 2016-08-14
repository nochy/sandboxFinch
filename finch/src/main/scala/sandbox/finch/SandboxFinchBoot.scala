package sandbox.finch

import com.twitter.finagle.Http
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import sandbox.finch.router.ExampleRouter

object SandboxFinchBoot extends TwitterServer {

  def main(): Unit = {

    val router = ExampleRouter()
    val service = router.service

    val server = Http.server.serve(":8080", service)


    closeOnExit(adminHttpServer)
    closeOnExit(server)
    Await.all(adminHttpServer)

  }

}
