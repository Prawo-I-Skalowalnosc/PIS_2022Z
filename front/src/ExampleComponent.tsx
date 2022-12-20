import {useEffect, useState} from "react";


export function ExampleComponent() {
    const [title, setTitle] = useState("");

    useEffect(() => {
        fetch("http://localhost:8090/movies/first")
            .then(res => res.json())
            .then(r => {
                setTitle(r.result);
            })
    }, [])

    return <div>{title}</div>;
}
