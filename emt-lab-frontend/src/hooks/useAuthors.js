import {useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";

const useAuthors = () => {
    const [authors, setAuthors] = useState([]);

    useEffect(() => {
        authorRepository
            .findAll()
            .then((response) => {
                setAuthors(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    return authors;
};

export default useAuthors;