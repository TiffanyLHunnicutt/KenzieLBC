import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class EvidenceClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getCase', 'getAllEvidenceForCase', 'getEvidenceById', 'createEvidence'];
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

    async getAllEvidenceForCase(caseId, errorCallback) {
        try {
            const response = await this.client.get(`/cases/${caseId}/evidence/all`);
            return response.data;
        } catch (error) {
            this.handleError("getAllEvidenceForCase", error, errorCallback)
        }
    }

               async getEvidenceById(id, errorCallback) {
                              try {
                                  const response = await this.client.get(`/cases/${caseId}/evidence/${evidenceId}`);
                                  return response.data;
                              } catch (error) {
                                  this.handleError("getEvidenceById", error, errorCallback)
                              }
                          }

    async createEvidence(caseId, author, description, location, timeDate, errorCallback) {
        try {
            const response = await this.client.post(`/cases/${caseId}/evidence`, {
                "author": author,
                "description": description,
                "location": location,
                "timeDate": timeDate,
            });
            return response.data;
        } catch (error) {
            this.handleError("createEvidence", error, errorCallback);
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