import java.io._
import json._

case class AutoMappingKeyword(keywordId: String, text: String) extends Serializable

case class AutoMappingAdGroup(adGroupId: String, baseAdGroupId: String, name: String, keywords: Option[List[AutoMappingKeyword]]) extends Serializable

case class AutoMappingCampaign(campaignId: String, baseCampaignId: String, name: String, keywords: Option[List[AutoMappingKeyword]], adGroups: Option[List[AutoMappingAdGroup]]) extends Serializable

case class AutoMappingStructure(parentCustomerId: String, baseCustomerId: String, campaigns: Option[List[AutoMappingCampaign]]) extends Serializable

object ParseJsonWithScalaJsonLib {
  def main(args: Array[String]): Unit = {

    implicit val accAutoMappingKeyword = ObjectAccessor.create[AutoMappingKeyword]
    implicit val accAutoMappingAdGroup = ObjectAccessor.create[AutoMappingAdGroup]
    implicit val accAutoMappingCampaign = ObjectAccessor.create[AutoMappingCampaign]
    implicit val accAutoMappingStructure = ObjectAccessor.create[AutoMappingStructure]

//    val sToReplace = "Promo Period - P7 - Brewer's Yeast  - MTE"
//    println(sToReplace.replace("'", "\'"))

    val jsonAll =
      """[{"parentCustomerId": "9924306543", "baseCustomerId": "12121212", "campaigns": [{"campaignId": "869204140", "baseCampaignId": "869204140", "name": "Campaign_name_1309_20170628_14166", "keywords": [{"keywordId": "394944186157555", "text": "Red_164_20170628_2682555"}], "adGroups": [{"adGroupId": "44190607376", "baseAdGroupId": "44190607376", "name": "AdGroup_name_1309_20170628_14166", "keywords": [] } ] }, {"campaignId": "869204365", "baseCampaignId": "869204365", "name": "Campaign_name_164_1_20170628_2682", "keywords": [], "adGroups": [{"adGroupId": "43919211316", "baseAdGroupId": "43919211316", "name": "AdGroup_name_164_1_20170628_2682", "keywords": [{"keywordId": "329502342173", "text": "Autumn_164_20170628_2682"}, {"keywordId": "329854534969", "text": "History_164_20170628_2682"} ] }, {"adGroupId": "49353568768", "baseAdGroupId": "49353568768", "name": "AdGroup_name_164_2_20170628_2682", "keywords": [{"keywordId": "394944186157", "text": "Red_164_20170628_2682"} ] } ] } ], "key": "9924306543;campaignManagement", "parentKey": "9924306543"}, {"parentCustomerId": "22222", "baseCustomerId": "55555", "campaigns": [{"campaignId": "869204140333", "baseCampaignId": "869204140333", "name": "Campaign_name_1309_20170628_14166333", "keywords": [], "adGroups": [{"adGroupId": "44190607376333", "baseAdGroupId": "44190607376333", "name": "AdGroup_name_1309_20170628_14166333", "keywords": [] } ] }, {"campaignId": "869204365444", "baseCampaignId": "869204365444", "name": "Campaign_name_164_1_20170628_2682444", "keywords": [], "adGroups": [{"adGroupId": "43919211316444", "baseAdGroupId": "43919211316444", "name": "AdGroup_name_164_1_20170628_2682444", "keywords": [{"keywordId": "329502342173444", "text": "Autumn_164_20170628_2682444"}, {"keywordId": "329854534969444", "text": "History_164_20170628_2682444"} ] }, {"adGroupId": "49353568768555", "baseAdGroupId": "49353568768555", "name": "AdGroup_name_164_2_20170628_2682555", "keywords": [] } ] } ], "key": "22222;campaignManagement", "parentKey": "22222"} ]""".stripMargin
    val tree = JValue.fromString(jsonAll).toObject[List[AutoMappingStructure]]

    //Serialization works
//    val out = new ObjectOutputStream(new FileOutputStream("/Users/eugene/Downloads/tree.obj"))
//    out.writeObject(tree)
//    out.close()
    //Deserialization works
//    val is = new ObjectInputStream(new FileInputStream("/Users/eugene/Downloads/tree.obj"))
//    val objectFromFile = is.readObject()
//    is.close()
//    println(objectFromFile)

    //!!!!!!!!!!
    //    val campaignId = "!!!!!!!!!!!!!"
    //    val campaignName = "Campaign_name_1309_20170628_14166333"
    //    val prodCustomerId = "55555"
    //
    //    var returnValue = campaignId
    //    tree
    //      .filter(structure => prodCustomerId.equals(structure.baseCustomerId))
    //      .filter(structure => structure.campaigns.nonEmpty)
    //      .foreach(structure => {
    //        structure.campaigns.get.foreach(campaign => {
    //          if (campaignName.equals(campaign.name)) {
    //            returnValue = campaign.campaignId
    //          }
    //        })
    //      })
    //    println(returnValue)


    //!!!!!!!!!!
    //    val adgroupId = "!!!!!!!!!!!!!"
    //    val adgroupName = "AdGroup_name_164_1_20170628_2682444"
    //    val campaignName = "Campaign_name_164_1_20170628_2682444"
    //    val prodCustomerId = "55555"
    //
    //    var returnValue = adgroupId
    //    tree
    //      .filter(structure => prodCustomerId.equals(structure.baseCustomerId))
    //      .filter(structure => structure.campaigns.nonEmpty)
    //      .foreach(structure => {
    //        structure.campaigns.get.foreach(campaign => {
    //          if (campaignName.equals(campaign.name)) {
    //            val adGroups = campaign.adGroups
    //            if (adGroups.nonEmpty) {
    //              val listOfAdGroups = adGroups.get
    //              listOfAdGroups.foreach(adGroup => {
    //                if (adgroupName.equals(adGroup.name)) {
    //                  returnValue = adGroup.adGroupId
    //                }
    //              })
    //            }
    //          }
    //        })
    //      })
    //    println(returnValue)


    //!!!!!!!!!!
    //    val keywordId = "!!!!!!!!!!!!!"
    //    val keywordName = "History_164_20170628_2682444"
    //    val adgroupName = "AdGroup_name_164_1_20170628_2682444"
    //    val campaignName = "Campaign_name_164_1_20170628_2682444"
    //    val prodCustomerId = "55555"
    //
    //    var returnValue = keywordId
    //    tree
    //      .filter(structure => prodCustomerId.equals(structure.baseCustomerId))
    //      .filter(structure => structure.campaigns.nonEmpty)
    //      .foreach(structure => {
    //        structure.campaigns.get.foreach(campaign => {
    //          if (campaignName.equals(campaign.name)) {
    //            val adGroups = campaign.adGroups
    //            if (adGroups.nonEmpty) {
    //              adGroups.get.foreach(adGroup => {
    //                if (adgroupName.equals(adGroup.name)) {
    //                  val keywords = adGroup.keywords
    //                  if (keywords.nonEmpty) {
    //                    keywords.get.foreach(keyword => {
    //                      if (keywordName.equals(keyword.text)) {
    //                        returnValue = keyword.keywordId
    //                      }
    //                    })
    //                  }
    //                }
    //              })
    //            }
    //          }
    //        })
    //      })
    //    println(returnValue)


    tree
      .filter(structure => structure.campaigns.nonEmpty)
      .foreach(structure => {
        val baseCustomerId = structure.baseCustomerId //it's a id of PROD
        val parentCustomerId = structure.parentCustomerId //it's a id of TEST

        structure.campaigns.get
          .filter(campaign => campaign.adGroups.nonEmpty)
          .foreach(campaign => {
            val campaignId = campaign.campaignId
            val campaignName = campaign.name

            campaign.adGroups.get
              .filter(adGroup => adGroup.keywords.nonEmpty)
              .foreach(adGroup => {
                val adGroupName = adGroup.name
                val adGroupId = adGroup.adGroupId

                adGroup.keywords.get.foreach(keyword => {
                  val keywordText = keyword.text
                  val keywordId = keyword.keywordId

                  val s = s"INSERT INTO TABLE adwords_search_query_emulated (SELECT $adGroupId as AdGroupId, AdGroup, AvgPosition, $campaignId as CampaignId, Campaign, Clicks, Conversions, ConversionsTotal, Cost, Day, Device, Impressions, Interactions, $keywordId as KeywordId, Keyword, Query, month, daySimple, $parentCustomerId as Customer_ID, year FROM adwords_search_query WHERE Customer_ID = $baseCustomerId AND Campaign = $campaignName AND AdGroup = $adGroupName AND Keyword = $keywordText)"
                  println(s)
                })
              })
          })
      })
  }
}