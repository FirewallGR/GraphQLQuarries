package ru.meowmure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetaComponent {
    String id;
    String name; // original file name for metadata perposes
    String uguid;
    String descriptionShort;
    String currentVersion;
    String architecture;
    String os;
    String fileSize;
    String fileId;
    String sha256;
    String locationPath; // full absolute path of the file including its name and extension. the short filename here is visible file name in OS
    String copyrightOrganizationId;


}