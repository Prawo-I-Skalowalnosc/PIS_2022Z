import {InputAdornment, Stack, SvgIcon, TextField} from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';

export function SearchField(props: {inputHandler: any}) {

    const sendData = (e: any) => {
        props.inputHandler(e.target.value.toLowerCase());
    }

    return (
    <Stack sx={{margin: "0 auto",
            border: "1px solid #000000",
            borderRadius: 1,
            width: 0.8,
            backgroundColor: "white"}}
        direction="row"
        justifyContent="flex-start"
        alignItems="center"
        spacing={0}>
        <TextField sx={{width: 1}}
        id="outlined-basic"
        label=""
        variant="outlined"
        InputProps={{
            startAdornment: (
                <InputAdornment position={"start"}>
                    <SvgIcon component={SearchIcon} fontSize="large"/>
                </InputAdornment>
            ),}}
        onChange={sendData}/>
    </Stack>
)
}

export default SearchField;
