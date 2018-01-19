import gspread
from oauth2client.service_account import ServiceAccountCredentials
import pprint
import csv
import pandas as pd
import xlwt
import json
import sys
import os

file_name = [ "Hub_Wise_Resolution", "CM Resolved", "Captain_Online", "CM_Wise_Resolution" ]
 
 # use creds to create a client to interact with the Google Drive API
scope = ['https://spreadsheets.google.com/feeds']
creds = ServiceAccountCredentials.from_json_keyfile_name('spreadsheet image converter-4e232796c3b4.json', scope)
gc = gspread.authorize(creds)
 


# spreadsheet = gc.open("The name of your spreadsheet")

# Find a workbook by name and open the first sheet
# Make sure you use the right name here.


# spreadsheet = gc.open("sample_sheet").get_worksheet(0)
# spreadsheet = gc.open("sample_sheet").worksheet("Sheet1")

spreadsheet = gc.open("Captain Adoption Dashboard")

for filename in file_name:

	print(filename)

	sh = spreadsheet.worksheet(filename)

	# for i, worksheet in enumerate(spreadsheet.worksheets()):
	#     filename = str(creds) + '-worksheet' + str(i) + '.csv'
	#     with open(filename, 'w') as f:
	#         writer = csv.writer(f)
	#         writer.writerows(worksheet.get_all_values())

	# pp = pprint.PrettyPrinter()	
		 
	# # # # Extract and print all of the values
	# list_of_lists = sh.get_all_values()
	list_of_hashes =  sh.get_all_records()

	s1 = json.dumps(list_of_hashes)

	json_imported = json.loads(s1)

	# pp.pprint(json_imported)

	workbook = xlwt.Workbook()
	worksheet = workbook.add_sheet('json exported')


	columns = list(json_imported[0].keys())

	i = 0
	for column in columns:
	    worksheet.write(0, i, column)
	    i += 1

	j = 1
	for row in json_imported:
	    i = 0
	    for column in columns:
	        worksheet.write(j, i, row[column])
	        i += 1
	    j += 1
	    
	workbook.save(filename.split('.')[0] + '.xls')
# pp.pprint(list_of_lists)
