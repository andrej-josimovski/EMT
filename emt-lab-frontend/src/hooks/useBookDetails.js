import {useState, useEffect} from 'react'
import bookRepository from "../repository/bookRepository.js";
import authorRepository from "../repository/authorRepository.js";

const useBookDetails = (id) => {
    const [state, setState] = useState({
        "book": null,
        "category": null,
        "author": null,
    });

    useEffect(() => {
        bookRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "book": response.data, "category": response.data.category}));
                authorRepository
                    .findById(response.data.author)
                    .then((response) => {
                        setState(prevState => ({...prevState, "author": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};
export default useBookDetails;