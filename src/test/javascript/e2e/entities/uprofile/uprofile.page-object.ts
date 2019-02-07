import { element, by, ElementFinder } from 'protractor';

export class UprofileComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-uprofile div table .btn-danger'));
    title = element.all(by.css('jhi-uprofile div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class UprofileUpdatePage {
    pageTitle = element(by.id('jhi-uprofile-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    imageInput = element(by.id('file_image'));
    genderSelect = element(by.id('field_gender'));
    phoneInput = element(by.id('field_phone'));
    bioInput = element(by.id('field_bio'));
    facebookInput = element(by.id('field_facebook'));
    twitterInput = element(by.id('field_twitter'));
    linkedinInput = element(by.id('field_linkedin'));
    instagramInput = element(by.id('field_instagram'));
    googlePlusInput = element(by.id('field_googlePlus'));
    birthdateInput = element(by.id('field_birthdate'));
    civilStatusSelect = element(by.id('field_civilStatus'));
    lookingForSelect = element(by.id('field_lookingFor'));
    purposeSelect = element(by.id('field_purpose'));
    physicalSelect = element(by.id('field_physical'));
    religionSelect = element(by.id('field_religion'));
    ethnicGroupSelect = element(by.id('field_ethnicGroup'));
    studiesSelect = element(by.id('field_studies'));
    sibblingsInput = element(by.id('field_sibblings'));
    eyesSelect = element(by.id('field_eyes'));
    smokerSelect = element(by.id('field_smoker'));
    childrenSelect = element(by.id('field_children'));
    futureChildrenSelect = element(by.id('field_futureChildren'));
    petInput = element(by.id('field_pet'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setImageInput(image) {
        await this.imageInput.sendKeys(image);
    }

    async getImageInput() {
        return this.imageInput.getAttribute('value');
    }

    async setGenderSelect(gender) {
        await this.genderSelect.sendKeys(gender);
    }

    async getGenderSelect() {
        return this.genderSelect.element(by.css('option:checked')).getText();
    }

    async genderSelectLastOption() {
        await this.genderSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setPhoneInput(phone) {
        await this.phoneInput.sendKeys(phone);
    }

    async getPhoneInput() {
        return this.phoneInput.getAttribute('value');
    }

    async setBioInput(bio) {
        await this.bioInput.sendKeys(bio);
    }

    async getBioInput() {
        return this.bioInput.getAttribute('value');
    }

    async setFacebookInput(facebook) {
        await this.facebookInput.sendKeys(facebook);
    }

    async getFacebookInput() {
        return this.facebookInput.getAttribute('value');
    }

    async setTwitterInput(twitter) {
        await this.twitterInput.sendKeys(twitter);
    }

    async getTwitterInput() {
        return this.twitterInput.getAttribute('value');
    }

    async setLinkedinInput(linkedin) {
        await this.linkedinInput.sendKeys(linkedin);
    }

    async getLinkedinInput() {
        return this.linkedinInput.getAttribute('value');
    }

    async setInstagramInput(instagram) {
        await this.instagramInput.sendKeys(instagram);
    }

    async getInstagramInput() {
        return this.instagramInput.getAttribute('value');
    }

    async setGooglePlusInput(googlePlus) {
        await this.googlePlusInput.sendKeys(googlePlus);
    }

    async getGooglePlusInput() {
        return this.googlePlusInput.getAttribute('value');
    }

    async setBirthdateInput(birthdate) {
        await this.birthdateInput.sendKeys(birthdate);
    }

    async getBirthdateInput() {
        return this.birthdateInput.getAttribute('value');
    }

    async setCivilStatusSelect(civilStatus) {
        await this.civilStatusSelect.sendKeys(civilStatus);
    }

    async getCivilStatusSelect() {
        return this.civilStatusSelect.element(by.css('option:checked')).getText();
    }

    async civilStatusSelectLastOption() {
        await this.civilStatusSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setLookingForSelect(lookingFor) {
        await this.lookingForSelect.sendKeys(lookingFor);
    }

    async getLookingForSelect() {
        return this.lookingForSelect.element(by.css('option:checked')).getText();
    }

    async lookingForSelectLastOption() {
        await this.lookingForSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setPurposeSelect(purpose) {
        await this.purposeSelect.sendKeys(purpose);
    }

    async getPurposeSelect() {
        return this.purposeSelect.element(by.css('option:checked')).getText();
    }

    async purposeSelectLastOption() {
        await this.purposeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setPhysicalSelect(physical) {
        await this.physicalSelect.sendKeys(physical);
    }

    async getPhysicalSelect() {
        return this.physicalSelect.element(by.css('option:checked')).getText();
    }

    async physicalSelectLastOption() {
        await this.physicalSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setReligionSelect(religion) {
        await this.religionSelect.sendKeys(religion);
    }

    async getReligionSelect() {
        return this.religionSelect.element(by.css('option:checked')).getText();
    }

    async religionSelectLastOption() {
        await this.religionSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setEthnicGroupSelect(ethnicGroup) {
        await this.ethnicGroupSelect.sendKeys(ethnicGroup);
    }

    async getEthnicGroupSelect() {
        return this.ethnicGroupSelect.element(by.css('option:checked')).getText();
    }

    async ethnicGroupSelectLastOption() {
        await this.ethnicGroupSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setStudiesSelect(studies) {
        await this.studiesSelect.sendKeys(studies);
    }

    async getStudiesSelect() {
        return this.studiesSelect.element(by.css('option:checked')).getText();
    }

    async studiesSelectLastOption() {
        await this.studiesSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setSibblingsInput(sibblings) {
        await this.sibblingsInput.sendKeys(sibblings);
    }

    async getSibblingsInput() {
        return this.sibblingsInput.getAttribute('value');
    }

    async setEyesSelect(eyes) {
        await this.eyesSelect.sendKeys(eyes);
    }

    async getEyesSelect() {
        return this.eyesSelect.element(by.css('option:checked')).getText();
    }

    async eyesSelectLastOption() {
        await this.eyesSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setSmokerSelect(smoker) {
        await this.smokerSelect.sendKeys(smoker);
    }

    async getSmokerSelect() {
        return this.smokerSelect.element(by.css('option:checked')).getText();
    }

    async smokerSelectLastOption() {
        await this.smokerSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setChildrenSelect(children) {
        await this.childrenSelect.sendKeys(children);
    }

    async getChildrenSelect() {
        return this.childrenSelect.element(by.css('option:checked')).getText();
    }

    async childrenSelectLastOption() {
        await this.childrenSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setFutureChildrenSelect(futureChildren) {
        await this.futureChildrenSelect.sendKeys(futureChildren);
    }

    async getFutureChildrenSelect() {
        return this.futureChildrenSelect.element(by.css('option:checked')).getText();
    }

    async futureChildrenSelectLastOption() {
        await this.futureChildrenSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    getPetInput() {
        return this.petInput;
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class UprofileDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-uprofile-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-uprofile'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
