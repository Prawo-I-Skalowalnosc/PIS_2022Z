import * as React from 'react';
import ReactStars from 'react-stars';
import { useState } from 'react';
import { Requests } from '../requests/Requests'
import { RateResponse } from '../types/Rate';
import {ErrorResponse} from "../types/ErrorResponse";

interface StarShowProps {
  rating: number;
  maxRating: number;
  size: number;
}

export function StarShow(props : StarShowProps) {
  return (
    <ReactStars 
      className='pis-stars' 
      count={props.maxRating}
      value={props.rating}
      size={props.size}
      color2="#ffb400"
      edit={false}
    />
  );
};


interface StarRatingProps {
  rater_id: string;
  movie_id: string;
  maxRating: number;
  size: number;
  onSuccess: (response: RateResponse) => void,
  onError: (err: ErrorResponse) => void
}

export function StarRating(props : StarRatingProps) {
  const [rankValue, setRankValue] = useState<number>(props.maxRating); 
  const handleClick = (new_rating : number) => {
    setRankValue(new_rating)
    Requests.sendRate({raterId : props.rater_id, movieId : props.movie_id, rating : new_rating}).then(res => {
      console.log(res)
      if (res.err) {
          props.onError(res.err);
      }
      else if (res.res){
          props.onSuccess(res.res)

      }
    });
  }
  
  return (
    <ReactStars
      className='pis-stars' 
      onChange={handleClick}
      count={props.maxRating}
      value={rankValue}
      size={props.size}
      color2="#ffb400"
      edit={true}
      half={false}
    />
  );
};