import { element, by, ElementFinder } from 'protractor';

export class CalbumComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-calbum div table .btn-danger'));
    title = element.all(by.css('jhi-calbum div h2#page-heading span')).first();

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

export class CalbumUpdatePage {
    pageTitle = element(by.id('jhi-calbum-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    creationDateInput = element(by.id('field_creationDate'));
    titleInput = element(by.id('field_title'));
    communitySelect = element(by.id('field_community'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setTitleInput(title) {
        await this.titleInput.sendKeys(title);
    }

    async getTitleInput() {
        return this.titleInput.getAttribute('value');
    }

    async communitySelectLastOption() {
        await this.communitySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async communitySelectOption(option) {
        await this.communitySelect.sendKeys(option);
    }

    getCommunitySelect(): ElementFinder {
        return this.communitySelect;
    }

    async getCommunitySelectedOption() {
        return this.communitySelect.element(by.css('option:checked')).getText();
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

export class CalbumDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-calbum-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-calbum'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
