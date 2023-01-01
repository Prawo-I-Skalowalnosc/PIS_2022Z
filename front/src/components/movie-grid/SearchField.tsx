import {Stack, SvgIcon, TextField} from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';

export function SearchField(props: {inputHandler: any}) {

    const sendData = (e: any) => {
        props.inputHandler(e.target.value.toLowerCase());
    }

    return (
    <Stack sx={{marginLeft: "5vw"}}
        direction="row"
        justifyContent="flex-start"
        alignItems="center"
        spacing={0}>
        <SvgIcon component={SearchIcon} fontSize="large"/>
        <TextField sx={{width: "90%", marginLeft: "1rem", backgroundColor: "white", borderRadius: "5px"}}
        id="outlined-basic"
        label="Search"
        variant="outlined"
        onChange={sendData}/>
    </Stack>
)
}

export default SearchField;
