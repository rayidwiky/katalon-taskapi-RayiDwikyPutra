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

// Membuat permintaan GET ke URL
ResponseObject response = WS.sendRequestAndVerify(findTestObject('GET/GET_album'))

// Memeriksa apakah respons memiliki status code 200 (OK)
WS.verifyResponseStatusCode(response, 200)

// Mendapatkan konten JSON dari respons
String jsonResponseContent = response.getResponseBodyContent()

// Menggunakan JsonParser untuk mengonversi konten JSON menjadi objek
def jsonParser = new JsonParser()
def jsonResponse = jsonParser.parseText(jsonResponseContent)

// Memeriksa apakah respons mengandung data album
assert jsonResponse.size() > 0 : "Response should contain at least one album."

// Memeriksa apakah properti tertentu dari setiap album sesuai dengan yang diharapkan
jsonResponse.each { album ->
	assert album.containsKey("userId") : "1"
	assert album.containsKey("id") : "1"
	assert album.containsKey("title") : "quidem molestiae enim"
}


