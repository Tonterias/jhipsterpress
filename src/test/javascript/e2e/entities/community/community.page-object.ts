import { element, by, ElementFinder } from 'protractor';

export class CommunityComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-community div table .btn-danger'));
    title = element.all(by.css('jhi-community div h2#page-heading span')).first();

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

export class CommunityUpdatePage {
    pageTitle = element(by.id('jhi-community-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    communityNameInput = element(by.id('field_communityName'));
    communityDescriptionInput = element(by.id('field_communityDescription'));
    imageInput = element(by.id('file_image'));
    isActiveInput = element(by.id('field_isActive'));
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

    async setCommunityNameInput(communityName) {
        await this.communityNameInput.sendKeys(communityName);
    }

    async getCommunityNameInput() {
        return this.communityNameInput.getAttribute('value');
    }

    async setCommunityDescriptionInput(communityDescription) {
        await this.communityDescriptionInput.sendKeys(communityDescription);
    }

    async getCommunityDescriptionInput() {
        return this.communityDescriptionInput.getAttribute('value');
    }

    async setImageInput(image) {
        await this.imageInput.sendKeys(image);
    }

    async getImageInput() {
        return this.imageInput.getAttribute('value');
    }

    getIsActiveInput() {
        return this.isActiveInput;
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

export class CommunityDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-community-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-community'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
