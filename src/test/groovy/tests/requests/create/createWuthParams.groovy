package tests.requests.create

import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.ResponseDto

import static tests.TestUtils.*;

Attrs attrs = new Attrs()
attrs.put("clientName", "Казанцев Егор")
attrs.put("clientEmail", "myemail@somedomain.com")
attrs.put("clientPhone", "8(800)555-35-35")
attrs.put("service", 'slmService$23495801')
attrs.put("agreement", 'agreement$12435404')
attrs.put("shortDescr", "Какая то тема")
attrs.put("descriptionRTF", "бр<br>бр")
ResponseDto.SingleValueResultWithParams result = connector.jsonRpcCreate('serviceCall$vnINC', attrs, ["UUID", "title"])
logger.info(result.result.toString())