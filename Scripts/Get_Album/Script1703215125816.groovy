import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import groovy.json.JsonBuilder
import groovy.json.JsonParser
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import groovy.json.JsonSlurper


// Ambil get_album
ResponseObject apiData = WS.sendRequest(findTestObject('GET/GET_album'))
println(apiData.getResponseText())

// Cek respone status
WS.verifyResponseStatusCode(apiData, 200)

// Konversi API dengan slurper
def jsonArray = new JsonSlurper().parseText(apiData.getResponseText())
print(jsonArray)

// Data yang akan diverifikasi 10
def expectedDataList = [
	[userId: 1, id: 1, title: "quidem molestiae enim"],
	[userId: 1, id: 2, title: "sunt qui excepturi placeat culpa"],
	[userId: 1, id: 3, title: "omnis laborum odio"],
	[userId: 1, id: 4, title: "non esse culpa molestiae omnis sed optio"],
	[userId: 1, id: 5, title: "eaque aut omnis a"],
	[userId: 1, id: 6, title: "natus impedit quibusdam illo est"],
	[userId: 1, id: 7, title: "quibusdam autem aliquid et et quia"],
	[userId: 1, id: 8, title: "qui fuga est a eum"],
	[userId: 1, id: 9, title: "saepe unde necessitatibus rem"],
	[userId: 1, id: 10, title: "distinctio laborum qui"],
	[userId: 1, id: 11, title: "distinctio laborum quiz"], //cek kondisi tidak ada
	[userId: 1, id: 19, title: "distinctio laborum quizz"], //cek kondisi tidak ada
]

// Iterasi 
expectedDataList.each { expectedData ->
	// set dibuat false dahulu, bila tidak ada data tetap false
	def dataFound = false

	// Iterasi array
	jsonArray.each { item ->
		if (item.userId == expectedData.userId && item.id == expectedData.id && item.title == expectedData.title) {
			// Set data ditemukan dan diganti true, keluar dari loop
			dataFound = true
			return
		}
	}
	// Verifikasi 
	if (dataFound) {
		WS.comment("Data ditemukan di API: ${expectedData}")		
	} else {
		WS.comment("Data tidak ditemukan di API: ${expectedData}")		
	}
}