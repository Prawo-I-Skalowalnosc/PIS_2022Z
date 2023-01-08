import ReactStars from 'react-stars';
import {useState} from 'react';
import {Requests} from '../requests/Requests'
import {UserRateResponse} from '../types/UserRate';
import {ErrorResponse} from "../types/ErrorResponse";
import '../style/UserRating.css'

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
    movie_id: string;
    maxRating: number;
    size: number;
    onSuccess: (response: UserRateResponse) => void,
    onError: (err: ErrorResponse) => void
    resetInfo: () => void
}

export function StarRating(props : StarRatingProps) {
    const [rankValue, setRankValue] = useState<number>(props.maxRating); 
    const handleClick = (new_rating : number) => {
        if (new_rating !== rankValue) {
            setRankValue(new_rating)
            Requests.sendRate({
                movieId : props.movie_id,
                rating : new_rating
            }).then( res =>{
                if (res.res){
                    props.onSuccess(res.res)
                } else if (res.err){
                    props.onError(res.err)
                }
            });
        }
        else props.resetInfo()
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
    />);
};