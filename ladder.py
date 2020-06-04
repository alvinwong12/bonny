import random as rd
### make amidakuji ladder

# can customize these parameters
h_bar = '___'
empty = '   '
v_bar = '|'
bars_amount = 7

# user input
members = list(input().strip())
# number of members + number columns
row_length = 2 * len(members) - 1
# height of ladder excluding first row and last row of the members
height = len(members) * 2 - 1

ladder = []
# make ladder without horizontal bars
# eg. grid
# a   b   c   d
# |   |   |   |
# |   |   |   |
for i in range(height):
	# when the index is divisible by 2, is a vertical bar; if not then its a empty bar
	ladder.append([v_bar if i%2 ==0 else empty for i in range(row_length) ])

# assign horizontal bars
# there are (height-1) * columns amount of possible places, each spot named by number starting at 0.
# |  0  |  1   |  2   |
# |  3  |  4   |  5   |
#           .
#           .
#           .
# number of columns = number of members - 1
# each number above is a number represent a location where can put a horizontal bar

h_bar_possible_locations = list(range((height -1) * (len(members) - 1)))
# ensure each column has one bar
# pick one for each column first
# loop through each column
for i in range(len(members) - 1):
	# add only when can add more
	while bars_amount > 0:
		# pick a random location on this column
		l = rd.randint(0, height - 2) * (len(members) - 1) + i
		# calculate row and column like below
		r,c  = int(l/(len(members) - 1)), 2*int(l%(len(members) - 1)) + 1
		if (c-2 <= 0 or ladder[r][c-2] != h_bar) and (c+2 >= len(ladder[r]) or ladder[r][c+2] != h_bar):
			ladder[r][c] = h_bar
			bars_amount -= 1
			h_bar_possible_locations.remove(l)
			# only add one
			break

# loop through all possible locations until:
# 1. Already added all the bars required
# 2. No more places to add leftover bars due
while h_bar_possible_locations and bars_amount > 0:
	# random chooses a location
	i = rd.choice(h_bar_possible_locations)
	# ladder is a 2D grid, so I need the row and column to identify the location on the grid
	# row (r) = location/number of columns
	# column (c) = 2 * remainder(location/number of columns) + 1
	r,c  = int(i/(len(members) - 1)), 2*int(i%(len(members) - 1)) + 1
	# check if for adjacent horizontal bar. This is not allowed
	if (c-2 <= 0 or ladder[r][c-2] != h_bar) and (c+2 >= len(ladder[r]) or ladder[r][c+2] != h_bar):
		# Add the horizontal bar if there is no adjacent horizontal bars
		ladder[r][c] = h_bar
		bars_amount -= 1
	# remove this location from consideration
	h_bar_possible_locations.remove(i)

# ladder -> 2D grid, each number is an entry on the grid, whether it is vertical/horizontal/empty bar
# * this location is different than the calculation just above for finding places to put horizontal bar
# 0   1   2   3   4   5 -> first row
# 6   7   8   9   10  11
# 12  13  14  15  16  17

# list of ending points at the bottom of the ladder
# list starting from 0...N where N=number of members - 1
# customizable
endpoints = list(map(str, range(len(members))))

### walk
def walk(member):
	# determine starting location. corresponds to the index in list of members
	# multiplied by 2 becauses ladder grid has columns between members
	# c is the location on the grid (only on vertical bars) mapped from the index
	c = members.index(member) * 2
	# going down the ladder
	for row in ladder:
		#  1 column left/right = horizontal bar since I standing on vertical bar
		# on my left is a horizontal bar, walk across
		if (c-1 >= 0) and (row[c-1] == h_bar):
			# move two columns to get to the vertical bar on the left
			c -= 2
			# continue to next row
			continue
		# on my right is a horizontal bar, walk across
		if (c+1 < len(row)) and (row[c+1] == h_bar):
			# move two columns to get to the vertical bar on the right
			c += 2
			continue
	# map the location on the grid back to the correspond index of the endpoints
	return int(c/2)

ends = {}
# store where each member ended
for member in members:
	ends[member] = endpoints[walk(member)]

### output
print(empty.join(members))
for row in ladder:
	print("".join(row))
print(empty.join(endpoints))

print("Value assignment")
for k,v in ends.items():
	print("{0}: {1}".format(k,v))
