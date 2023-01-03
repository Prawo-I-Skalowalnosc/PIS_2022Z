import * as React from 'react';
import ReactStars from 'react-stars';
import { useState } from 'react';

interface StarShowProps {
  rating: number;
  maxRating: number;
  size: number;
}

export function StarShow(props : StarShowProps) {
  return (
    <ReactStars 
      count={props.maxRating}
      value={props.rating * props.maxRating}
      size={props.size}
      color2="#ffb400"
      edit={false}
    />
  );
};


interface StarRatingProps {
  maxRating: number;
  size: number;
}

export function StarRating(props : StarRatingProps) {
  const [rankValue, setRankValue] = useState<number>(props.maxRating / 2); 

  const handleClick = (new_rating : number) => {
    setRankValue(new_rating)  
  }
  
  return (
    <ReactStars onChange={handleClick}
      count={props.maxRating}
      value={rankValue}
      size={props.size}
      color2="#ffb400"
      edit={true}
    />
  );
};