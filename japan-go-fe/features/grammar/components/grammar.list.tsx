"use client";
import React from "react";
import WrapBox from "@/components/ui/wrap.box";
import { GrammarResponse } from "@/types/api/responses/grammar.response";
import SingleGrammar from "@/features/grammar/components/single.grammar";
import { Pagination } from "@mui/material";
import { useRouter, useSearchParams } from "next/navigation";
import { usePathname } from "@/i18n/navigation";
import { PageDetailsResponse } from "@/types/api/responses/base.response";

const GrammarList = ({
    page,
}: {
    page: PageDetailsResponse<GrammarResponse[]>;
}) => {
    const searchParams = useSearchParams();
    const { replace } = useRouter();
    const pathname = usePathname();

    const handleChange = (event: React.ChangeEvent<unknown>, value: number) => {
        const params = new URLSearchParams(searchParams);
        params.set("page", value.toString());
        replace(`${pathname}?${params}`);
    };

    return (
        <WrapBox>
            <div className="flex flex-col gap-y-3">
                {page.content.map((grammar, index) => {
                    return (
                        <SingleGrammar
                            grammar={grammar}
                            index={
                                (page.currentPage - 1) * page.pageSize +
                                index +
                                1
                            }
                            key={grammar.id}
                        />
                    );
                })}
            </div>

            <div className="mt-5 flex justify-end">
                <Pagination
                    count={page.totalPages}
                    color="primary"
                    shape="rounded"
                    showFirstButton
                    showLastButton
                    onChange={handleChange}
                    page={page.currentPage}
                />
            </div>
        </WrapBox>
    );
};

export default GrammarList;
