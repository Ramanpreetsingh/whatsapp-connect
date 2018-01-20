import gspread
from oauth2client.service_account import ServiceAccountCredentials
import pprint
import csv
import pandas as pd
import xlwt
import json
import sys
import os
import plotly
import plotly.plotly as py
import plotly.figure_factory as ff
from plotly.graph_objs import *
from plotly.offline import download_plotlyjs, init_notebook_mode, plot, iplot, offline
from plotly.graph_objs import Bar, Scatter, Figure, Layout


file_name = [ "Monthly View" ]

plotly.tools.set_credentials_file(username='nimxor', api_key='NkcGBrnfkMsO6iELCZxd')
 
 # use creds to create a client to interact with the Google Drive API
scope = ['https://spreadsheets.google.com/feeds']
creds = ServiceAccountCredentials.from_json_keyfile_name('spreadsheet image converter-4e232796c3b4.json', scope)
gc = gspread.authorize(creds)

# spreadsheet = gc.open("The name of your spreadsheet")

# Find a workbook by name and open the first sheet
# Make sure you use the right name here.


# spreadsheet = gc.open("sample_sheet").get_worksheet(0)
# spreadsheet = gc.open("sample_sheet").worksheet("Sheet1")

spreadsheet = gc.open("Demand Products - Performance Tracker")

for filename in file_name:

	print(filename)

	sh = spreadsheet.worksheet(filename)

	# for i, worksheet in enumerate(spreadsheet.worksheets()):
	#     filename = str(creds) + '-worksheet' + str(i) + '.csv'
	#     with open(filename, 'w') as f:
	#         writer = csv.writer(f)
	#         writer.writerows(worksheet.get_all_values())

	pp = pprint.PrettyPrinter()	
		 
	# # # # Extract and print all of the values
	# list_of_lists = sh.get_all_values()
	list_of_hashes =  sh.get_all_records()

	s1 = json.dumps(list_of_hashes)

	json_imported = json.loads(s1)

	workbook = xlwt.Workbook()
	worksheet = workbook.add_sheet('json exported')

	columns = list(json_imported[0].keys())

	columns = columns[2:]

	pp.pprint(columns)

	identifiers = ["URN", "ROOM_GMV", "TAKE_RATE", "ARR"]

	ls = [1,3,5,6]
	i = 0

	for identifier in identifiers:
		sample = []
		for column in columns:
			sample.append(str(json_imported[ls[i]][column]))
		i += 1
		pp.pprint(sample)
		data = [Bar(x=columns, y = sample)]
		layout = Layout(title=filename+' of '+identifier)
		fig = Figure(data=data,layout=layout)
		py.image.save_as(fig, str(filename)+'_'+identifier + '.png')

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
