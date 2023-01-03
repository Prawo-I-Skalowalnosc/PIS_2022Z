curl -H 'Content-Type: application/json'  -H 'Accept: application/json' -X PUT \
    -d '{"raterId": "a45da728-3a40-4c2c-8028-946ca33be960",
    	 "movieId": "c2d29867-3d0b-d497-9191-28a9d8ee7833",
    	 "rating": 2 }' \
0.0.0.0:8090/movieRatings/addRating
