package tests.requests.create

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.RpcRequestDto
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto

Logger logger = LoggerFactory.getLogger("CREATE TEST")
new ConnectorParams()
//Создается ассотиативный массив для редактирования,
//содержий в ключах коды атрибутов,
//в значениях значения атрибутов создаваемого объекта
Attrs attrs = new Attrs()
        .put("clientName", "Казанцев Егор")
        .put("clientEmail", "myemail@somedomain.com")
        .put("clientPhone", "8(800)555-35-35")
        .put("service", 'slmService$23495801')
        .put("agreement", 'agreement$12435404')
        .put("shortDescr", "Какая то тема")
        .put("descriptionRTF", "бр<br>бр")

//Создается новый коннектор
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))
//Создается DTO метода create для последующей отправки
RpcRequestDto.Create dto = new RpcRequestDto.Create('serviceCall$vnINC', attrs)
//Запрос отправляется с ранее созданным DTO
RpcResponseDto result = connector.sendRequest(dto)

logger.info(new ObjectMapper().writeValueAsString(result))