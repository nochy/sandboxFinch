package sandbox.finch

import com.twitter.util.Future
import sandbox.thrift.ExampleService

class Example extends ExampleService.FutureIface {
  override def show(): Future[Unit] = {
    Future.value(())
  }
}

