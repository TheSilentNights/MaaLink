import axios, {type AxiosResponse} from "axios";

export async function sendPost(url: string, data:any, withToken: boolean): Promise<AxiosResponse<any, any>> {

    if (withToken) {
        return axios.post(url, data, {
            headers: {
                "Content-Type": "application/json",
                "token": localStorage.getItem("token"),
            },
        });
    } else {
        return axios.post(url, data);
    }
}