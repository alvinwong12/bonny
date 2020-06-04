import json
import urllib.request
import requests
from string import punctuation

def count_occurences(jsonurl, word):
	w = word.lower()
	with urllib.request.urlopen(jsonurl) as fh:
		source = fh.read()
	data = json.loads(source)
	lst = []
	wordlst = []
	for i in data:
		lst.append(i['text'].lower())
	lst = ["".join(c for c in s if c not in punctuation) for s in lst]

	for j in lst:
		# for word in j.split():
		for word in j.split(" "):
			wordlst.append(word)
	return wordlst.count(w)

def count_occurences_alvin(jsonurl, word):
	word = word.lower()
	tweets = requests.get(jsonurl).json()

	words = []
	for tweet in tweets:
		t = tweet['text'].lower().split(" ")
		words += ["".join(c for c in s if c not in punctuation) for s in t]
	count = 0
	for w in words:
		if w == word:
			count += 1
	return count 

def words_per_day(jsonurl, word):
	word = word.lower()
	tweets = requests.get(jsonurl).json()

	days = {}
	words = []
	for tweet in tweets:
		t = tweet['text'].lower().split(" ")
		words += [("".join(c for c in s if c not in punctuation), tweet['day']) for s in t]

	for w, day in words:
		if w == word:
			days[day] = days.get(day, 0) + 1
	return days


jsonurl = "https://dl.dropboxusercontent.com/s/mihdrnsgb6ebauf/trump_tweets.json"
word = "america"

assert count_occurences_alvin(jsonurl, word) == 284
assert count_occurences(jsonurl, word) == 284
assert count_occurences_alvin(jsonurl, word) == sum(words_per_day(jsonurl, word).values())
assert count_occurences(jsonurl, word) == sum(words_per_day(jsonurl, word).values())

