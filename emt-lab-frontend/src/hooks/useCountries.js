import {useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";

const useCountries = () => {
    const [countries, setCountries] = useState([]);

    useEffect(() => {
        countryRepository
            .findAll()
            .then((response) => {
                setCountries(response.data);
            })
            .catch((error) => console.log(error));
    }, []);

    return countries;
};

export default useCountries;