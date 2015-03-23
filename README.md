# pair

Connect 4 Game
==============

<h4>Authors</h4>
Yakov Boglev - ybogle01
Monty West - mwest06

<h4>Recommended AI Settings</h4>
Settings 1-5 for the AI work very quickly, there is a visible sow down at AI 6, but it is still quick enough to play. AI 7 and up are significantly slower.

<h4>Notes:</h4>
<ul>
	<li><b>AISpec tests</b> - Instead of using asserts, we decided to assess the tree by eye. However as the tree prints to a hardcoded filename, we could only run one test at a time.</li>
	<li><b>AI minimax method</b> - Originally we used array.maxBy / array.minBy inside the player match statement to complete the assignment of value. However, as the match statement's only purpose is to decide between min and max, we instead used the match statement to assign a lambda, which we later used in a map/reduce call. This means the assignment (which occurs in every case) is outside of the match statement, we believe this is clearer and separates concerns better.</li>
</ul>
