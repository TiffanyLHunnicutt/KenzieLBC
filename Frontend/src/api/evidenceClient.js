import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class EvidenceClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getAllEvidenceForCase', 'getEvidenceById', 'createEvidence'];
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
             async getAllEvidenceForCase(id, errorCallback) {
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
             async createEvidence(author, description, location, timeDate, potentialSuspects, errorCallback) {
                     try {
                         const response = await this.client.post(`/cases/${caseId}/evidence`, {

                         title: title,
                         author: author,
                         description: description,
                         location: location,
                         timeDate: timeDate,
                         potentialSuspects: potentialSuspects
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