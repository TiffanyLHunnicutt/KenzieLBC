import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class CaseClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getCase', 'getAllOpenCases', 'createCase', 'updateCase'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    async getCase(caseId, errorCallback) {
        try {
            const response = await this.client.get(`/cases/${caseId}`);
            return response.data;
        } catch (error) {
            this.handleError("getCase", error, errorCallback)
        }
    }

    async getAllOpenCases(errorCallback) {
        try {
            const response = await this.client.get(`/cases/all`);
            return response.data;
        } catch(error) {
            this.handleError("getAllOpenCases", error, errorCallback);
        }
    }

    async createCase(title, author, description, location, timeDate, potentialSuspects, errorCallback) {
        try {
            const response = await this.client.post(`cases`, {
                title: title,
                author: author,
                description: description,
                location: location,
                timeDate: timeDate,
                potentialSuspects: [potentialSuspects]
            });
            return response.data;
        } catch (error) {
            this.handleError("createCase", error, errorCallback);
        }
    }

    async updateCase(caseId, description, potentialSuspects, openCase, errorCallback) {
        try {
            const response = await this.client.put(`/cases/${caseId}`, {
                description: description,
                potentialSuspects: potentialSuspects,
                openCase: openCase
            });
            return response.data;
        } catch (error) {
            this.handleError("updateCase", error, errorCallback);
        }
    }

    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}