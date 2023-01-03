import * as React from 'react';
import ReactStars from 'react-stars';
import { useState } from 'react';
interface StarRatingProps {
  maxRating: number;
  size: number;
}

export function StarShow(props : StarRatingProps) {
  const [rankValue, setRankValue] = useState<number>(props.maxRating / 2); 

  return (
    <ReactStars 
      count={props.maxRating}
      value={rankValue}
      size={props.size}
      color2="#ffb400"
      edit={false}
    />
  );
};

export function StarRating(props : StarRatingProps) {
  const [rankValue, setRankValue] = useState<number>(props.maxRating / 2); 

  const handleClick = (new_rating : number) => {
    setRankValue(new_rating)
    console.log(new_rating)
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