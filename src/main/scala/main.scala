import com.typesafe.config.ConfigFactory
import scala.concurrent._
import scala.concurrent.duration._

object Main {
	import scala.concurrent.ExecutionContext.Implicits.global

	def main(args: Array[String]) {
		val config = ConfigFactory.load()
		val webapiKey = config.getString("allegro.webapiKey")
	  	val login = config.getString("allegro.login")
	  	val password = config.getString("allegro.password")
	  	val countryCode = 1
	  	val service = (new allegro.ServiceBindings with
	  			scalaxb.Soap11ClientsAsync with
	  			scalaxb.DispatchHttpClientsAsync {}
	  		).service
		val p = for {
			status <- service.doQuerySysStatus(3, countryCode, webapiKey) 
			login <- service.doLogin(login, password, countryCode, webapiKey, status.verKey)
		} yield login
		
		val response = Await.result(p, 5 seconds)
		println(response)
	}
}