import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object ParseJsonWithJackson {
  def main(args: Array[String]): Unit = {

    val jsonAll =
      """[{"parentCustomerId": "9924306543", "baseCustomerId": "12121212", "campaigns": [{"campaignId": "869204140", "baseCampaignId": "869204140", "name": "Campaign_name_1309_20170628_14166", "keywords": [{"keywordId": "394944186157555", "text": "Red_164_20170628_2682555"}], "adGroups": [{"adGroupId": "44190607376", "baseAdGroupId": "44190607376", "name": "AdGroup_name_1309_20170628_14166", "keywords": [] } ] }, {"campaignId": "869204365", "baseCampaignId": "869204365", "name": "Campaign_name_164_1_20170628_2682", "keywords": [], "adGroups": [{"adGroupId": "43919211316", "baseAdGroupId": "43919211316", "name": "AdGroup_name_164_1_20170628_2682", "keywords": [{"keywordId": "329502342173", "text": "Autumn_164_20170628_2682"}, {"keywordId": "329854534969", "text": "History_164_20170628_2682"} ] }, {"adGroupId": "49353568768", "baseAdGroupId": "49353568768", "name": "AdGroup_name_164_2_20170628_2682", "keywords": [{"keywordId": "394944186157", "text": "Red_164_20170628_2682"} ] } ] } ], "key": "9924306543;campaignManagement", "parentKey": "9924306543"}, {"parentCustomerId": "22222", "baseCustomerId": "55555", "campaigns": [{"campaignId": "869204140333", "baseCampaignId": "869204140333", "name": "Campaign_name_1309_20170628_14166333", "keywords": [], "adGroups": [{"adGroupId": "44190607376333", "baseAdGroupId": "44190607376333", "name": "AdGroup_name_1309_20170628_14166333", "keywords": [] } ] }, {"campaignId": "869204365444", "baseCampaignId": "869204365444", "name": "Campaign_name_164_1_20170628_2682444", "keywords": [], "adGroups": [{"adGroupId": "43919211316444", "baseAdGroupId": "43919211316444", "name": "AdGroup_name_164_1_20170628_2682444", "keywords": [{"keywordId": "329502342173444", "text": "Autumn_164_20170628_2682444"}, {"keywordId": "329854534969444", "text": "History_164_20170628_2682444"} ] }, {"adGroupId": "49353568768555", "baseAdGroupId": "49353568768555", "name": "AdGroup_name_164_2_20170628_2682555", "keywords": [] } ] } ], "key": "22222;campaignManagement", "parentKey": "22222"} ]""".stripMargin

    val mapper = new ObjectMapper() // with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val tree = mapper.readValue(jsonAll, classOf[List[Map[String, Any]]])
    for (tupleOfCustomer <- tree) {
      var parentCustomerId = "" //it's a id of TEST
      var baseCustomerId = "" //it's a id of PROD
      for ((key, value) <- tupleOfCustomer) {
        if (key.equals("parentCustomerId")) {
          parentCustomerId = value.toString
        }
        if (key.equals("baseCustomerId")) {
          baseCustomerId = value.toString
        }
        if (!parentCustomerId.equals("") && !baseCustomerId.equals("")) {
          //TODO: remove old rows from DB by parentCustomerId
          //TODO: copy as new rows by baseCustomerId-to-parentCustomerId
        }
      }

      for ((key, value) <- tupleOfCustomer) {

        if (key.equals("campaigns")) {
          val iteratorCampaigns = value.asInstanceOf[List[scala.collection.immutable.HashMap.HashTrieMap[String, Any]]].iterator
          while (iteratorCampaigns.hasNext) {
            val trieMap = iteratorCampaigns.next()
            val iteratorTupleOfCampaigns = trieMap.iterator

            var campaignId = ""
            var baseCampaignId = ""
            var campaignName = ""
            while (iteratorTupleOfCampaigns.hasNext) {
              val tupleOfCampaign = iteratorTupleOfCampaigns.next()
              if (tupleOfCampaign._1.equals("campaignId")) {
                println(tupleOfCampaign._1 + ":" + tupleOfCampaign._2)
                campaignId = tupleOfCampaign._2.toString
              }
              else if (tupleOfCampaign._1.equals("baseCampaignId")) {
                println(tupleOfCampaign._1 + ":" + tupleOfCampaign._2)
                baseCampaignId = tupleOfCampaign._2.toString
              }
              else if (tupleOfCampaign._1.equals("name")) {
                println(tupleOfCampaign._1 + ":" + tupleOfCampaign._2)
                campaignName = tupleOfCampaign._2.toString
              }
              else if (tupleOfCampaign._1.equals("keywords")) {
                println(tupleOfCampaign._1 + ":" + tupleOfCampaign._2)

//                val iteratorCampaignKeywords = tupleOfCampaign._2.
//                while (iteratorCampaignKeywords.hasNext) {
//                  val campaignKeyword = iteratorCampaignKeywords.next()
//                  println(campaignKeyword)
//                }
                //TODO
              }
              else if (tupleOfCampaign._1.equals("adGroups")) {
                val iteratorAdGroups = tupleOfCampaign._2.asInstanceOf[List[Any]].toIterator
                while(iteratorAdGroups.hasNext) {
                  val adGroups = iteratorAdGroups.next()
                  val iteratorAdGroupsTuples = adGroups.asInstanceOf[Map[Any,Any]].iterator
                  println(adGroups)
                }
                //TODO
              }
            }
          }
        }
      }
    }

    println(tree)

  }
}