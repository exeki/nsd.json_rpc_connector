package tests.requests.edit

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams
import ru.kazantsev.nsd.json_rpc_connector.Attrs
import ru.kazantsev.nsd.json_rpc_connector.Condition
import ru.kazantsev.nsd.json_rpc_connector.Connector
import ru.kazantsev.nsd.json_rpc_connector.Query
import ru.kazantsev.nsd.json_rpc_connector.RpcResponseDto
import ru.kazantsev.nsd.json_rpc_connector.RpcUtilities

Logger logger = LoggerFactory.getLogger("CREATE TEST")

RpcUtilities rpcUt = RpcUtilities.getInstance()

//Создается ассотиативный массив для редактирования,
//содержий в ключах коды атрибутов,
//в значениях значения атрибутов создаваемого объекта
Attrs attrs = rpcUt.attrs()
        .put("shortDescr", "123131sdfsdfsdfsdfsf23123")
        .put("descriptionRTF", "3213123asfasfwsdefs123123")

//Создается поисковый массив при помощи утилитарного класса,
//в него добавляется условие соответствия, которое так же создается при помощи утилитарного класса
Query query = rpcUt.query().put("UUID", new Condition.Eq('serviceCall$59374017'))

//Создается новый коннектор
Connector connector = new Connector(ConnectorParams.byConfigFile("DSO_TEST"))

//Запрос отправляется без предвариательно создания DTO
RpcResponseDto result = connector.jsonRpcEdit(
        "serviceCall",
        query,
        attrs,
        null,
        null)

logger.info(new ObjectMapper().writeValueAsString(result))